using CountWord.CountWord.Contract;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
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
        void ICountWord.CountWordFork(string[] words)
        {
            words.ToList().FindAll(x => x.Length >= lenghtWord).ForEach(word =>
            {
                wordLenght.AddOrUpdate(word, 1, (_, y) => y + 1);
            });
            print(GetWord());
        }
        private string GetWord() {
             
            Dictionary<string, int> concurrent = wordLenght.ToDictionary(kvp => kvp.Key,
                                                                         kvp => kvp.Value);
            return string.Join("\n", concurrent.OrderByDescending(x => x.Value)
                         .Take(qtaWord).Select(kvp => $"word= {kvp.Key} total={kvp.Value}").ToArray());
        }
         
    }
}
