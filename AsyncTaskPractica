# Practica

## 1 - Fibonacci

```java

    public void calcular(View v) {
        EditText etEntrada = (EditText) findViewById(R.id.et_entrada);
        int number = Integer.parseInt(etEntrada.getText().toString());
        int result = fibonacci(number);
        Toast.makeText(MainActivity.this, "Calculado! result: "+result, Toast.LENGTH_SHORT).show();
    }


    public int fibonacci(int n) {
        if (n==0 || n==1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
```
