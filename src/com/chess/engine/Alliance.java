package com.chess.engine;

// Better, since it is type safe

public enum Alliance {
    WHITE {
        @Override
        public int getDirection() {
            return -1; // For white will return negative 1, my idea is that this is a multiplier for the direction vector
        }

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }
    },
    BLACK {
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public int getDirection() {
            return 1; // For black, will return positive 1
        }
    };

    public abstract int getDirection(); // Making an abstract method in an enum makes you implement the method for each instance in the enum
    public abstract boolean isWhite();
    public abstract boolean isBlack();
}
