using CountWord.CountWord.Contract;
using CountWord.CountWord.Util;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CountWord.CountWord.Implementation
{
    public class ReadDirectory :AbstractExecutionContext, IReadDirectory
    { 
        private readonly ICountWord Count;
        public ReadDirectory(ICountWord Count) => this.Count = Count;

        public async void ReadDirectoryRecursive(string path = "C:\\")
        {
            List<string> allDirectoryAndFile = GetAllFileAndDirectory(path);
            await ReadAllRecursive(allDirectoryAndFile);

        }
        private async Task CallReadFile(string path)=>
            await Task.Run(async() => await new ReadFile(Count).ReadAllFile(path));

        
        private async Task ReadAllRecursive(List<string> paths)
        {
            var result = paths.Select((path) =>
            { 
                try
                { 
                    return (path switch
                    {
                        string route when File.GetAttributes(route).HasFlag(FileAttributes.Directory) =>
                            Task.Run(() => ReadAllRecursive(GetAllFileAndDirectory(route).ToList())),
                        string route when route.EndsWith(".txt") =>
                           CallReadFile(route),
                        _ => Task.CompletedTask

                    });
                }
                catch
                {
                    return Task.CompletedTask;
                }
            }).ToList();
            await Task.WhenAll(result);

        }

        private static List<string> GetAllFileAndDirectory(string path) => Directory.GetFiles(path)
                .Concat(Directory.GetDirectories(path)).ToList();

    }
}
