import java.util.Arrays;


public class LogisticRegression {

	/**data
	 * 0.8   0.2   0.3   0.1   0.1   1
	 * 0.3   0.1   0.4   0.3  0.2    1 
	 * 0.1   0.2   0.3   0.3   0.7   0
	 * 0.1   0.3   0.1   0.5  0.7    0
	 */
	public static void main(String[] args) {
		float[] w =  {0.1f,0.1f,0.1f,0.1f,0.1f,0.1f};
		float[][]x  = {{0.8f,0.2f,0.3f,0.1f,0.1f,1f},{0.3f,0.1f,0.4f,0.3f,0.2f,1f},{0.1f,0.2f,0.3f,0.3f,0.7f,1f},{0.1f,0.3f,0.1f,0.5f,0.7f,1f}};
		float[] label = {1,1,0,0};
		int i = 0;
       while(i<100){
		    w=getNewW(w,x,label);
		    System.out.println("w :"+Arrays.toString(w));
		    System.out.println("y : "+Arrays.toString(printy(w,x)));
		    System.out.println("loss £º"+loss(w,x,label));
		     i++;
       }   
	}
	 public static float[]   printy(float[] w,float[][] x){
		      float[] y = new float[x.length];
		       for(int i = 0 ;i < x.length;i++){
		    	         float predict = 0;
		    	          for(int j = 0;j<x[i].length;j++){
		    	        	      predict+=w[j]*x[i][j];
		    	          }
		    	          y[i] = sigmod(predict);
		       }
		       return y;
	 }
	public static  float loss(float[] w,float[][] x,float[] label){
		float losssum = 0;
		for(int i = 0;i<label.length;i++){
			   int j = 0;
			   float sum = 0;
			   while(j<w.length){
				           sum+=w[j]*x[i][j];
				           j++;
			   }
			  losssum += label[i]*Math.log(sigmod(sum))+(1-label[i])*Math.log(1-sigmod(sum));
			  
		}
		 return -losssum/4;
	}
	public static  float[]  getNewW(float[] w,float[][] x,float[] label){
		 float[] gradient = new float[w.length];
		  float a = 0.4f;
         float[] temp = new float[x.length];
          for(int i = 0; i<x.length;i++){
        	      float[] xi = x[i];
        	      float sum = 0;
        	      for(int j = 0;j <xi.length;j++){
        	    	      sum+=w[j]*xi[j];
        	      } 
        	      temp[i]= label[i]-sigmod(sum);      	        
         }
         for(int i = 0;i<gradient.length;i++){
        	         float sum = 0;
        	         for(int j = 0;j<x.length;j++){
        	        	         sum+=x[j][i]*temp[j];
        	         }
        	         gradient[i] = sum;
         }
         for(int i = 0;i<w.length;i++){
        	  w[i]=w[i]+a*gradient[i];
         }
         return w;
	}
	public static float sigmod(float x){
		      return (float) (1/(1+Math.exp(-x)));
	}

}
