using CountWord.Factory;
using System;
using System.Diagnostics;
using System.Threading;

namespace CountWord2
{
    class Program
    {
        static void Print(string totalWord)
        {
            //Console.Clear();
            //Console.WriteLine(totalWord);
        }

        public static void Main(string[] arg)
        {
             
            if (arg.Length>0 && bool.Parse(arg[0]))
            {

            Console.WriteLine(111111);
                SubscriberFactory.Instance.GetReadDirectory(Print).ReadDirectoryRecursive2();
            }
            else if (arg.Length>0 && bool.Parse(arg[1]))
            {

            Console.WriteLine(22222);
                SubscriberFactory.Instance.GetReadDirectory(Print).ReadDirectoryRecursive3();
            }
            else
            {

            Console.WriteLine(33333);
                SubscriberFactory.Instance.GetReadDirectory(Print).ReadDirectoryRecursive();
            }
            Console.ReadLine();
            Console.WriteLine("Hello World!");
        }
    }
}
