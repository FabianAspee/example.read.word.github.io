using CountWord.CountWord.Contract;
using CountWord.CountWord.Util;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace CountWord.CountWord.Implementation
{
    public class ReadFile :AbstractExecutionContext, IReadFile
    { 
        private static ICountWord CountWordFork;
        private const int DEFAULT_LINES = 100;
        public ReadFile(ICountWord Count) => CountWordFork = Count;
        public async Task ReadAllFile(string root)
        {
            string[] lines = await System.IO.File.ReadAllLinesAsync(root);
            await SplitFile(lines);
        }
        private static async Task SplitFile(string[] lines)
        {
            var lenght = lines.Length;
            if (lenght > DEFAULT_LINES)
            {
                var quotient = Math.DivRem(lenght, DEFAULT_LINES, out int remainder);
                var task = Enumerable.Range(0, quotient + 1)
                    .Select(index => index < quotient ? lines.Skip(DEFAULT_LINES * index).Take(DEFAULT_LINES).ToArray() : lines.Skip(lenght - remainder).Take(lenght - remainder).ToArray())
                    .ToList().Select(lines => CallCountWord(lines)).ToList();
                await Task.WhenAll(task);
                
            }
            else if (lenght > 0)
            {
                await CallCountWord(lines);
            }
            await Task.CompletedTask;
        }
        private static async Task CallCountWord(string[] lines) => await Task.Run(async () => await CountWordFork.CountWordFork(
                    lines.SelectMany(x => SplitString(x)).ToArray()));
        private static string[] SplitString(string line)
        {
            string regex = @"(\s|\p{P})+";
            return Regex.Split(line.Trim(), regex).Where(word => !Regex.IsMatch(word, regex)).ToArray();

        }
    }
}
