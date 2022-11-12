package oop.y2022.concurrency;


public class Main {

    public static final int CHUNKS_NUMBER = 100;
    public static final int SIZE = (Integer.MAX_VALUE >> 1) / CHUNKS_NUMBER * CHUNKS_NUMBER;
    public static final boolean[] FLAGS = new boolean[SIZE];

    /**
     * Пример многопоточного приложения.
     * Несколько потоков разбивают общий массив на подмассивы и заполняют его данными независимо.
     */
    public static void main(String[] args) throws InterruptedException {

        int chunkSize = SIZE / CHUNKS_NUMBER;

        for (int i = 0; i < CHUNKS_NUMBER; i++) {
            int iEf = i;
            // создаем объект Thread и запускаем его
            new Thread(() -> {
                // внутри потока заполняем подмассив
                for (int j = 0; j < chunkSize; j++) {
                    FLAGS[iEf*chunkSize + j] = true;
                }

            }).start();
        }

        // хак, ожидаем когда другие потоки закончат работу
        Thread.sleep(10_000);

        // проверяем, что данные корректно заполнены
        for (int i = 0; i < SIZE; i++) {
            if (!FLAGS[i]) {
                throw new RuntimeException(String.valueOf(i));
            }
        }
    }
}
