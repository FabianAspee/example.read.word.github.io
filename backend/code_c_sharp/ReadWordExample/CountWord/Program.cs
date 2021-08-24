using CountWord.Factory;
using System;

namespace CountWord
{
    class Program
    {
        private static void Print(string stampa)
        {
            Console.WriteLine(stampa);
        }
        static void Main()
        {
            SubscriberFactory.Instance.GetReadDirectory(Print).ReadDirectoryRecursive();
            Console.ReadLine();
            Console.WriteLine("Hello World!");
        }
    }
}
