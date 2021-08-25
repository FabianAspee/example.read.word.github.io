using CountWord.Factory;
using System;
using System.Threading;

namespace CountWord
{
    class Program
    {
        static void Print(string totalWord)
        {
            Console.Clear();
            Console.WriteLine(totalWord);
        }
        
        public static void Main()
        {
            Console.WriteLine(111111);
            Thread.Sleep(1000);
            Console.Clear();
            //SubscriberFactory.Instance.GetReadDirectory(Print).ReadDirectoryRecursive();
            Console.ReadLine();
            Console.WriteLine("Hello World!");
        }
    }
}
