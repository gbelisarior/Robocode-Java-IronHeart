package Ironheart;

import robocode.*;
import robocode.util.*;
import java.awt.Color;

/**
 * Ironheart - a class by (Everton, Gariel, Weverton)
 */

public class Ironheart extends AdvancedRobot {
    private ScannedRobotEvent alvoMaisProximo;
    private int tempoInatividade = 0;
  

    public void run() {
        // Configurações iniciais
       setColors(Color.red, Color.blue, Color.white, Color.yellow, Color.white);
 // Define as cores do seu tanque

        // Movimentação do tank em loop
        while (true) {
            boolean angulo = true; // Declare angulo dentro do loop

            if (angulo) {
                for (int voltasDir = 0; voltasDir < 4; voltasDir++) {
                    ahead(100);
                    turnRight(90);
                    turnGunRight(90);
                }
                for (int voltasEsq = 4; voltasEsq > 0; voltasEsq--) {
                    ahead(100);
                    turnLeft(90);
                    turnGunLeft(90);
                }
                angulo = false;
            } else {
                for (int voltasDir45 = 0; voltasDir45 < 8; voltasDir45++) {
                    ahead(50);
                    turnRight(45);
                    turnGunRight(90);
                }
                for (int voltasEsq45 = 4; voltasEsq45 > 0; voltasEsq45--) {
                    ahead(50);
                    turnLeft(45);
                    turnGunLeft(90);
                }
                angulo = true;
            }
            execute(); // Execute após definir os comandos
        }
    }

    public void onScannedRobot(ScannedRobotEvent event) {
        // Captura o nome do robô escaneado
        String roboTank = event.getName();

        // Captura a distância do robô escaneado
        double distancia = event.getDistance();

        // Exibe o nome do robô e a distância no console
        System.out.println(roboTank + " distancia " + distancia);

        // Ajuste o radar para travar no inimigo
        setTurnRadarRight(Utils.normalRelativeAngleDegrees(event.getBearing() + getHeading() - getRadarHeading()));

        // Trava a mira no alvo
        setTurnGunRight(Utils.normalRelativeAngleDegrees(event.getBearing() + getHeading() - getGunHeading()));

        // Se o inimigo estiver mirando em nós, recue
        if (event.getDistance() < 200 && event.getEnergy() > getEnergy()) {
            back(100); // Recua por um período
        } else {
            // Dispara um tiro com força 3 se a distância for menor que 135, caso contrário, dispara um tiro com força 1
            if (distancia < 135) {
                setFire(3); // Tiro com força 3
            } else {
                setFire(1); // Tiro com força 1
            }
        }
    }

    // Colisão com a parede
    public void onHitWall(HitWallEvent e) {
        back(50); // Recua 50 pixels
        turnRight(90); // Gira 90 graus à direita
    }

}