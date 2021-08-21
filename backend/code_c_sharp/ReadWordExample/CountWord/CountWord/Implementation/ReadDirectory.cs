using CountWord.CountWord.Contract;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace CountWord.CountWord.Implementation
{
    public class ReadDirectory : IReadDirectory
    {
        private readonly ConcurrentQueue<Task> TaskList = new();
        private ReadFile ReadFileFork;
        private readonly ICountWord Count;
        public ReadDirectory(ICountWord Count) => this.Count = Count;

        public async void ReadDirectoryRecursive(string path = "C:\\")
        {
            List<string> allDirectoryAndFile = GetAllFileAndDirectory(path);
            await ReadAllRecursive(allDirectoryAndFile).ContinueWith(x => Trace.WriteLine(x.ToString(), TaskList.Count.ToString()));

        }
        public Task ReadAllRecursive(List<string> paths)
        {
            paths.ForEach(path =>
            {
                try
                {
                    FileAttributes attr = File.GetAttributes(path);
                    if (attr.HasFlag(FileAttributes.Directory))
                        ReadAllRecursive(GetAllFileAndDirectory(path));
                    else if (path.EndsWith(".txt"))
                    {
                        ReadFileFork = new ReadFile(Count);
                        TaskList.Enqueue(Task.Run(async () => await ReadFileFork.ReadAllFile(path)));
                    }
                }
                catch (Exception e)
                {
                    e.ToString();
                }

            });
            if (!TaskList.IsEmpty) return Task.WhenAll(TaskList.ToArray());
            else
                return Task.CompletedTask;
        }
        private static List<string> GetAllFileAndDirectory(string path)=> Directory.GetFiles(path)
                .Concat(Directory.GetDirectories(path)).ToList();
         
    }
}
