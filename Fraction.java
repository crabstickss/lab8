class Fraction {
    int denominator;  //Знаменатель
    int numerator;  //Числитель

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    private void shortenFraction() {
        if (numerator >= denominator) {
            for (int i = numerator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    numerator = numerator / i;
                    denominator = denominator / i;
                }
            }
        } else {
            for (int i = denominator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    numerator = numerator / i;
                    denominator = denominator / i;
                }
            }
        }
    }

    public static Fraction SolveExpression(String expr) {
        int sign_index = expr.indexOf("+");
        if (sign_index == -1) {
            sign_index = expr.indexOf("-");
        } if (sign_index == -1) {
            sign_index = expr.indexOf("*");
        } if (sign_index == -1) {
            sign_index = expr.indexOf(":");
        }
        char sign = expr.charAt(sign_index);
        String first_f = expr.substring(0, sign_index);
        String second_f = expr.substring(sign_index + 1);

        String[] first_frac = first_f.split("/", 2);
        String[] second_frac = second_f.split("/", 2);

        int first_numerator = Integer.parseInt(first_frac[0]);
        int first_denominator = Integer.parseInt(first_frac[1]);
        if (first_denominator==0){
            System.out.println("Error! Try typing the expression again.");
            System.exit(0);
        }
        int second_numerator = Integer.parseInt(second_frac[0]);

        int second_denominator = Integer.parseInt(second_frac[1]);
        if (second_denominator==0){
            System.out.println("Error! Try typing the expression again.");
            System.exit(0);
        }

        Fraction first_fraction = new Fraction(first_numerator, first_denominator);
        Fraction second_fraction = new Fraction(second_numerator, second_denominator);
        Fraction res = new Fraction(1, 1);

        if (sign == '+') {
            res = first_fraction.add(second_fraction);
        } else if (sign == '-') {
            res = first_fraction.sub(second_fraction);
        } else if (sign == '*') {
            res = first_fraction.multy(second_fraction);
        } else if (sign == ':') {
            res = first_fraction.div(second_fraction);
        }
        return res;
    }

    private int GDC(int a, int b) { //Наибольший общий делитель

        return b == 0 ? a : GDC(b, a % b);
    }

    private int LCM(int a, int b) { //Наибольшее общее кратное

        return a / GDC(a, b) * b;
    }


    public Fraction add(Fraction frac) {
        int firstNum = this.numerator;
        int firstDen = this.denominator;

        int secondNum = frac.numerator;
        int secondDen = frac.denominator;

        Fraction result = new Fraction(1, 1);

        if (firstDen == secondDen) { //если знаменатели одинаковые
            result.denominator = firstDen;
            result.numerator = firstNum + secondNum;
        } else {
            int LCM = LCM(firstDen, secondDen);
            result.denominator = LCM;
            result.numerator = firstNum * (LCM / firstDen) + secondNum * (LCM / secondDen);
        }
        return result;
    }

    public Fraction sub(Fraction frac) {

        int firstNum = this.numerator;
        int firstDen = this.denominator;

        int secondNum = frac.numerator;
        int secondDen = frac.denominator;

        Fraction result = new Fraction(1, 1);

        if (firstDen == secondDen) { //если знаменатели одинаковые
            result.denominator = firstDen;
            result.numerator = firstNum - secondNum;
        } else {
            int LCM = LCM(firstDen, secondDen);
            result.denominator = LCM;
            result.numerator = firstNum * (LCM / firstDen) - secondNum * (LCM / secondDen);
        }
        return result;

    }


    public Fraction multy(Fraction frac) {

        int firstNum = this.numerator;
        int firstDen = this.denominator;

        int secondNum = frac.numerator;
        int secondDen = frac.denominator;

        Fraction result = new Fraction(1, 1);
        result.denominator = firstDen * secondDen;
        result.numerator = firstNum * secondNum;
        return result;

    }


    public Fraction div(Fraction frac) {
        int firstNum = this.numerator;
        int firstDen = this.denominator;

        int secondNum = frac.numerator;
        int secondDen = frac.denominator;

        Fraction result = new Fraction(1, 1);
        result.denominator = firstDen * secondNum;
        result.numerator = firstNum * secondDen;
        return result;
    }



    public String toString() {
        shortenFraction();
        return numerator + "/" + denominator;
    }


}