import java.lang.Math;

class Vampire{

  public static boolean isVampire(int prod, int i, int j){
    int len = (int)Math.log10((double)prod)+1;
    if(len%2!=0) return false;
    int val=0;
    int count[] = new int[10];
    for (; i>0;i/=10) {
      count[i%10]++;
    }
    for (; j>0; j/=10) {
      count[j%10]++;
    }
    for (; prod>0; prod/=10) {
      count[prod%10]--;
    }

    for (int x=0; x<10; x++) {
      if(count[x]!=0) val++;
    }

    if(val==0) return true;

    else return false;
  }

  public static void vampire(int num)
  {
    for (int x=1; num>0; x++) {
      for (int i=(int)Math.pow(10,x-1); i<(int)Math.pow(10,x); i++) {
        for (int j=(int)Math.pow(10,x-1); j<(int)Math.pow(10,x); j++) {
          if (i%10==0 && j%10==0) {continue;}

          if(isVampire(i*j,i,j))
          {
            System.out.println(i*j + " is a vampire number");
            num--;
          }

        }
      }
    }
  }

  public static void main(String[] args) {
      vampire(100);
  }
}