public class ThreadDemo
{
        public static void main(String [] args)
        {
                Dog dog = new Dog(10);
                Bird bird = new Bird(10);
                Thread t1 = new Thread(dog);
                Thread t2 = new Thread(bird);
                t1.start();
                t2.start();
        }
}

class Dog implements Runnable
{
        int n = 0, times = 0;
        public Dog(int n)
        {
                this.n = n;
        }
        public void run()
        {
                while(true)
                {
                    try{
                            Thread.sleep(1000);
                    }catch (Exception e)
                    {

                    }
                    times++;
                    System.out.println("Insert");
                    if(times == 10) break;
            }

    }
}

class Bird implements Runnable
{
    int n = 0, res = 0, times = 0;
    public Bird (int n)
    {
            this.n = n;
    }
    public void run()
    {
            while(true)
            {
                    try{
                            Thread.sleep(1000);
                    }catch (Exception e)
                    {

                    }
                    res = res + (++times);
                    System.out.println("Result = "+ res);
                    if(times == 10)
                    {
                            break;
                    }
            }
    }
}


