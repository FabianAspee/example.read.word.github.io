using CountWord.CountWord.Contract;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace CountWord.CountWord.Implementation
{
    public class ReadFile : IReadFile
    {
        private readonly ConcurrentQueue<Task> TaskList = new();
        private static ICountWord CountWordFork;
        private const int DEFAULT_LINES = 100;
        public ReadFile(ICountWord Count) => CountWordFork = Count;
        public async Task ReadAllFile(string root)
        {
            string[] lines = System.IO.File.ReadAllLines(root);
            await SplitFile(lines);
        }
        private Task SplitFile(string[] lines)
        {
            var lenght = lines.Length;
            if (lenght > DEFAULT_LINES)
            {
                var quotient = Math.DivRem(lenght, DEFAULT_LINES, out int remainder);
                for (int i = 0; i < quotient; i++)
                {
                    SplitFile(lines.Skip(DEFAULT_LINES * i).Take(DEFAULT_LINES).ToArray());
                }

                SplitFile(lines.Skip(lenght - remainder).Take(lenght - remainder).ToArray());
            }
            else if (lenght > 0)
            {
                TaskList.Enqueue(Task.Run(() => CountWordFork.CountWordFork(
                  lines.SelectMany(x => SplitString(x)).ToArray())));
            }

            return Task.Factory.ContinueWhenAll(TaskList.ToArray(), tasks => true); 

        }

        private static string[] SplitString(string line)
        {
            string regex = @"(\s|\p{P})+";
            return Regex.Split(line.Trim(), regex).Where(word => !Regex.IsMatch(word, regex)).ToArray();

        }
    }
}
