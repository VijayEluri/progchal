package progchal.ch1;

/**
 * 1.6.4
 */
public class Lcd {

    private final int s;
    private final int lastRow;
    private String output;

    public static void main(String[] args) {
        Lcd lcd = new Lcd(42902287, 5);
        System.out.println(lcd.toString());
    }

    Lcd(int n, int s) {
        this.s = s;
        lastRow = 2 * s + 2;
        output = renderAllRows(toDigitsArray(n));
    }

    private String renderAllRows(int[] digits) {
        StringBuilder allDigitsBuf = new StringBuilder();

        for (int row = 0; row < lastRow + 1; row++) {
            StringBuilder rowBuf = new StringBuilder();

            for (int i = 0; i < digits.length; i++) {
                drawRow(digits[i], i == digits.length - 1, row, rowBuf);
            }

            allDigitsBuf.append(rowBuf);
        }

        return allDigitsBuf.toString();
    }

    private int[] toDigitsArray(int n) {
        String s1 = String.valueOf(n);
        int[] digits = new int[s1.length()];
        int i = 0;

        for (char c : s1.toCharArray()) {
            digits[i++] = Character.digit(c, 10);
        }
        return digits;
    }

    @Override
    public String toString() {
        return output;
    }

    private void drawRow(int digit, boolean lastDigit, int row, StringBuilder buf) {
        if (row == 0) {
            drawTopRow(digit, buf);
        } else if (row == s + 1) {
            drawMiddleRow(digit, buf);
        } else if (row == lastRow) {
            drawBottomRow(digit, buf);
        } else if (row <= s) {
            drawTopInteriorRow(digit, buf);
        } else {
            drawBottomInteriorRow(digit, buf);
        }
        if (!lastDigit) {
            buf.append(' ');
        } else {
            buf.append('\n');
        }
    }

    private void drawTopRow(int n, StringBuilder buf) {
        switch (n) {
            case 1:
            case 4:
                emptyAcross(buf);
                return;
            default:
                lineAcross(buf);
        }
    }

    private void drawBottomRow(int n, StringBuilder buf) {
        switch (n) {
            case 1:
            case 4:
            case 7:
                emptyAcross(buf);
                return;
            default:
                lineAcross(buf);
        }
    }

    private void drawMiddleRow(int n, StringBuilder buf) {
        switch (n) {
            case 1:
            case 7:
            case 0:
                emptyAcross(buf);
                return;
            default:
                lineAcross(buf);
        }
    }

    private void drawTopInteriorRow(int n, StringBuilder buf) {
        switch (n) {
            case 1:
            case 2:
            case 3:
            case 7:
                edgeSegments(buf, false, true);
                return;
            case 5:
            case 6:
                edgeSegments(buf, true, false);
                return;
            default:
                edgeSegments(buf, true, true);
        }
    }

    private void drawBottomInteriorRow(int n, StringBuilder buf) {
        switch (n) {
            case 2:
                edgeSegments(buf, true, false);
                return;
            case 6:
            case 8:
            case 0:
                edgeSegments(buf, true, true);
                return;
            default:
                edgeSegments(buf, false, true);
        }
    }

    private void edgeSegments(StringBuilder buf, boolean left, boolean right) {
        if (left) {
            buf.append('|');
        } else {
            buf.append(' ');
        }
        repeat(' ', s, buf);
        if (right) {
            buf.append('|');
        } else {
            buf.append(' ');
        }
    }

    private void emptyAcross(StringBuilder buf) {
        repeat(' ', s + 2, buf);
    }

    private void lineAcross(StringBuilder buf) {
        buf.append(' ');
        repeat('-', s, buf);
        buf.append(' ');
    }

    private void repeat(char c, int n, StringBuilder b) {
        for (int i = 0; i < n; i++) {
            b.append(c);
        }
    }
}
