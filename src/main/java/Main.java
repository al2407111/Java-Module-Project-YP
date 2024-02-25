
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
public class Main {
    public static void main(String[] args) {

        Calculate calculate1 = new Calculate();
        Scanner scanner = new Scanner(System.in);
        int   people=0;

        while (true) {

            System.out.println("На скольких человек необходимо разделить счёт?");

            String peopleString = scanner.nextLine();
            try{
                people= Integer.parseInt(peopleString);
                if (people < 1) {
                    System.out.println("Это некорректное значение для подсчёта");

                } else if (people == 1) {
                    System.out.println("Нет смысла ничего считать и делить");

                } else if (people > 1) {

                    calculate1.calculateProductСost(people);

                    break;
                }

            }catch (NumberFormatException ex){

                System.out.print("Ошибка ввода. Введите корректное количество людей!!!\n");
            }



        }
    }

}

class Calculate {
    Double sumPrice = 0.0;
    Scanner scanner = new Scanner(System.in);
    int i = 0;
    HashMap<Integer, String> inputProduct = new HashMap<>();
    HashMap<Integer, Double> inputPrice = new HashMap<>();

    public   void calculateProductСost(double people) {

        while (true) {

            i++;
            String name ="";

            while ((name.trim().isEmpty())) {
                System.out.println("Введите название товара:");
                name = scanner.nextLine();
                if ((name.trim().isEmpty())) {
                    System.out.println("Ошибка ввода. Введите корректное название товара:");
                }
            }
            if ("Завершить".equalsIgnoreCase(name)) {
                break;
            }
            // System.out.println(name.trim().isEmpty());//проверка на коректный ввод

            inputProduct.put(i, name);


            Double inpСost = 0.0;
            while (true) {
                System.out.println("Введите  стоимость:");

                String inpСost1 = scanner.nextLine();

                if (isNumeric(inpСost1)) {//
                    inpСost = Double.parseDouble(inpСost1);



                    if ((inpСost >= 0)&&(((Math.floor(inpСost*1000)/10) % 1)==0) )  {  //почемуто происходит непонятно что без укругления

                        break;

                    }

                }
                System.out.println("Стоимость должна быть в формате рубли.копейки, например 10.45 или 11.40!! ");
            }




            inputPrice.put(i, inpСost);


            System.out.println("Товар  " + inputProduct.get(i) + " стоимостью  " + inputPrice.get(i) + " " +formatAmount(inpСost)+"  добавлен успешно.");



            sumPrice = sumPrice + inputPrice.get(i);


            System.out.println("Общая сумма " + (Math.floor(sumPrice * 100.0) / 100.0) +" " +formatAmount(sumPrice)+ "\nДобавить ещё один товар?");

            // scanner.nextLine();
            String name1 = scanner.nextLine();

            if ("Завершить".equalsIgnoreCase(name1)) {

                break;
            }



        }



        System.out.println("Список товаров");
        Collection<String> values = inputProduct.values();
        for (String value : values) {
            System.out.println(value);
        }
        // String roundSumPrice = String.format("%.02f", (sumPrice) / people);
        System.out.println("Средний чек " +(Math.floor((sumPrice / people)*100)/100) +" " +formatAmount((sumPrice) / people));
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static String formatAmount(double formatPrise) {//Сумма считается в рублях. Ваша задача — обработать правильный вывод. Если сумма 1.45, то вы должны вывести "1.45 рубль", а если сумма будет 3.20 или 4.00, вы должны вывести 3.20 рубля или 4.00 рубля.
        int   d1=100;
        if(formatPrise<100){
            d1=10;
        }

        double number = formatPrise;
        double roundedNumberPrice = number /d1;
        double integralPart = Math.round(((formatPrise- (formatPrise - (roundedNumberPrice % 1)))*d1));//на что заканчивается последние 2  цифры
        String currency = "";


        if (integralPart>=15) {
            double   roundedIng=integralPart/10;
            integralPart =Math.round(integralPart-(integralPart-(roundedIng% 1)*10));
        }
        if (formatPrise<15) {

            integralPart =Math.floor(formatPrise);
        }

        if ((integralPart==0)|| ((integralPart>=5) && (integralPart<=14)))
        {currency = "рублей";}
        else if ((integralPart>1) && (integralPart<=4))
        {currency = "рубля";}
        else if ((integralPart==1))
        {currency = "рубль";}

        return (currency);
    }
}