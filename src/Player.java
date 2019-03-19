public class Player {
    private char shape;
    public void setShape(char shape) {
        this.shape = shape;
    }
    public char getShape() {
        return shape;
    }

    public int[] getDecision() {
        return new int[]{0, 0};
    }


}

class Man extends Player {
    @Override
    public int[] getDecision() {
        Settings.output("Enter move for " + getShape() + " (RowCol):");
        String positionString = Settings.scanner.nextLine().toLowerCase();
        while (positionString.length() != 2 || positionString.charAt(0) < 97 || positionString.charAt(0) > 96 + Settings.MAX_DIMENSION ||
                positionString.charAt(1) < 97 || positionString.charAt(1) > 96 + Settings.MAX_DIMENSION) {
            Settings.output("Illegal position! Please input again.");
            positionString = Settings.scanner.nextLine().toLowerCase();
        }
        int[] arr = new int[]{positionString.charAt(0) - 97, positionString.charAt(1) - 97};
        return arr;
    }
}

class Computer extends Player {
    @Override
    public int[] getDecision() {
        ////////////////////////////////////////////////////////////////////
        return new int[]{0, 0};
    }
}