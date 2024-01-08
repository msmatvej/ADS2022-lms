package by.it.group251051.maslov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    // Метод для слияния и подсчета инверсий
    private int mergeAndCountInversions(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Создаем временные подмассивы
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Копируем данные во временные массивы
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int count = 0; // Число инверсий

        // Слияние временных массивов
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
                count += n1 - i; // Если элемент из правого подмассива меньше, то увеличиваем число инверсий
            }
            k++;
        }

        // Копируем оставшиеся элементы из leftArr, если таковые есть
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы из rightArr, если таковые есть
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }

        return count;
    }

    // Метод для сортировки слиянием и подсчета инверсий
    private int mergeSortAndCountInversions(int[] arr, int left, int right) {
        int count = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            // Рекурсивно сортируем левую и правую части
            count += mergeSortAndCountInversions(arr, left, mid);
            count += mergeSortAndCountInversions(arr, mid + 1, right);

            // Объединяем отсортированные части и считаем инверсии
            count += mergeAndCountInversions(arr, left, mid, right);
        }

        return count;
    }

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Размер массива
        int n = scanner.nextInt();
        int[] a = new int[n];

        // Заполняем массив
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Вызываем метод сортировки слиянием и подсчета инверсий
        return mergeSortAndCountInversions(a, 0, n - 1);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
