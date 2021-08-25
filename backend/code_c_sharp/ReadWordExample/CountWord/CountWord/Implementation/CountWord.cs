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
            await Task.Run(async () =>
            {
                Parallel.ForEach(words.ToList().FindAll(x => x.Length >= lenghtWord), word =>
                {
                    wordLenght.AddOrUpdate(word, 1, (_, y) => y + 1);
                });
                print(await GetWord());
            });

        }
        private async Task<string> GetWord()
        { 
            return string.Join("\n", (await Task.WhenAll(Task.Run(() => wordLenght.OrderByDescending(x => x.Value)
                            .Take(qtaWord).Select(kvp => $"word= {kvp.Key} total={kvp.Value}").ToArray()))).SelectMany(x => x).ToArray());


        }

    }
}
