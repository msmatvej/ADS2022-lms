package by.it.a_khmelev.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }
    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (n <= 1) {
            return n;
        }

        int periodLength = findPisanoPeriodLength(m);
        int remainder = (int) (n % periodLength);

        long[] fib = new long[periodLength + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= periodLength; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % m;
        }

        return fib[remainder];
    }
    private static int findPisanoPeriodLength(int m) {
        int a = 0, b = 1, c = a + b;
        for (int i = 0; i < m * m; i++) {
            c = (a + b) % m;
            a = b;
            b = c;
            if (a == 0 && b == 1) {
                return i + 1;
            }
        }
        return -1;
    }

//        return 0L;
    }


