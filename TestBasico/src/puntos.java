public class puntos {
	
	public static void main(String[] args) {
		
		//Ordenamiento de vectores
		
		//crear vector de 100 posiciones
		int[] numeros= new int[100];
		//Carga de valores random entre 1 y 100000
		for(int i=0; i<numeros.length; i++) {
			numeros[i]= (int) (Math.random()*100000+1);
		}
		//Ordenamiento
		numeros=quickSort(numeros,0,99);
		System.out.println("Ordenamiento de vectores");
		//Impresión de resultado
		for(int i=0; i<numeros.length; i++) {
			System.out.println(String.valueOf(i+1)+ "-"+numeros[i]);
		}
		
		//Operaciones Aritmeticas
		System.out.println("\nOperaciones Aritmeticas: Potencia");
		System.out.println(potencia(4,4));
		
	}
	
	//metodo de ordenamiento
	public static int[] quickSort(int numeros[], int izq, int der) {
		int pivote=numeros[izq]; // se toma el primer elemento como pivote
		int i=izq;         //  variable para realizar la búsqueda de izquierda a derecha
		int j=der;         // variable para realizar la búsqueda de derecha a izquierda
		int aux;
		 
		while(i < j){                               
		  while(numeros[i] <= pivote && i < j) i++; 
		  while(numeros[j] > pivote) j--;          
		     if (i < j) {                                          
		         aux= numeros[i];                      // intercambio
		         numeros[i]=numeros[j];
		         numeros[j]=aux;
		     }
		   }
		   
	    numeros[izq]=numeros[j];                          
		numeros[j]=pivote;      
		   
		if(izq < j-1)
		   quickSort(numeros,izq,j-1);          // ordenamiento para el subarray izquierdo
		if(j+1 < der)
		   quickSort(numeros,j+1,der);          // ordenamiento para el subarray derecho
		return numeros;
		
	}
	
	public static int potencia(int a,int b) {
		
		int resultado = 0, acumulado=1;
		
		for (int i = 0; i < b; i++) {
			resultado=0;
			for (int j = 0; j <a; j++) {
				resultado+=acumulado;
			}
			acumulado=resultado;
			
		}
		return resultado;
		
	}

}
