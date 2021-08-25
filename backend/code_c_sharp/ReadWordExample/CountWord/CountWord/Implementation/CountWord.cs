using CountWord.CountWord.Contract;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CountWord.CountWord.Implementation
{
    public class CountWord: ICountWord
    {
        private readonly int qtaWord;
        private readonly int lenghtWord;
        private readonly Action<string> print;
        private readonly ConcurrentDictionary<string, int> wordLenght = new();
        public CountWord(int qtaWord, int lenghtWord, Action<string> print) =>
            (this.qtaWord, this.lenghtWord, this.print) = (qtaWord, lenghtWord, print);
        async Task ICountWord.CountWordFork(string[] words)
        {
            //Console.WriteLine(ThreadPool.ThreadCount);
            await Task.Run(async () =>
            {
                words.ToList().FindAll(x => x.Length >= lenghtWord).ForEach(word =>
                {
                    wordLenght.AddOrUpdate(word, 1, (_, y) => y + 1);
                });
                print(await GetWord());
            });
           
        }
        private async Task<string> GetWord() {
             
            Dictionary<string, int> concurrent = wordLenght.AsParallel().ToDictionary(kvp => kvp.Key,
                                                                         kvp => kvp.Value);
         
            return string.Join("\n", await Task.Run(() => concurrent.OrderByDescending(x => x.Value)
                          .Take(qtaWord).Select(kvp => $"word= {kvp.Key} total={kvp.Value}").ToArray()));
        }
         
    }
}
