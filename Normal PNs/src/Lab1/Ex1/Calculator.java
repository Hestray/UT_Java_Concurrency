package Lab1.Ex1;

public class Calculator {
    float addition(float x, float y) {
        return x + y;
    }

    float multiplication(float x, float y)  {
        return x * y;
    }
    
    float subtraction(float x, float y)  {
        return x - y;
    }
    
    float division(float x, float y)  {
        if (y == 0) {
            System.out.println("Division by zero is impossible. Please reconsider.");
            return 0;
        }
        else return x / y;
    }
}
