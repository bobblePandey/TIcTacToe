package Dtos;

import Models.Cell;
import Models.Move;

import java.util.Scanner;

public class NextMoveInput {

    public static Move getNextHumanPlayerInput() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter row : ");
        int row = sc.nextInt();
        System.out.println("Enter col: ");
        int col = sc.nextInt();

        return new Move(new Cell(row, col));
    }




}
