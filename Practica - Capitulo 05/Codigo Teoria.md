# Practica

## 1 - Fibonacci

Fibonacci de forma secuencial o sincrónica

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

## 1.1 - Fibonacci Async

Creamos la clase dentro de la actividad (InnerClass)
```java
class FibonacciTask extends AsyncTask<Integer, Void, Integer> {


        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Calculando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            return fibonacci(integers[0].intValue());
        }


        protected void onPostExecute(Integer result) {
            Toast.makeText(MainActivity.this, "Resultado: "+result, Toast.LENGTH_SHORT).show();
        }
    }
}
```
