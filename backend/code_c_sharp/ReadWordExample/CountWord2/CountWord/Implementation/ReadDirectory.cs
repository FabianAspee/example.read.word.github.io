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
        private readonly ConcurrentQueue<Task> TaskList = new();
        private readonly ConcurrentBag<string> FilesSystemTXT = new();
        private readonly ICountWord Count;
        private int count = 0;
        public ReadDirectory(ICountWord Count) => this.Count = Count;

        public void ReadDirectoryRecursive(string path = "C:\\")
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();
            List<string> allDirectoryAndFile = GetAllFileAndDirectory(path);

            ReadAllRecursive(allDirectoryAndFile, stopWatch);

        }
        public void ReadDirectoryRecursive2(string path = "C:\\")
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start(); 
            
            List<string> allDirectoryAndFile = GetAllFileAndDirectory(path);
            ReadAllDirectory(allDirectoryAndFile, stopWatch);

        }
        public async void ReadDirectoryRecursive3(string path = "C:\\")
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start(); 
            
            List<string> allDirectoryAndFile = GetAllFileAndDirectory(path);
            await ReadAllRecursiveTask(allDirectoryAndFile, stopWatch);

        }
        private async Task CallReadFile(string path)=>
            await Task.Run(async() => await new ReadFile(Count).ReadAllFile(path));
        
        private void ReadAllRecursive(List<string> paths, Stopwatch stopWatch)
        {
           
            Parallel.ForEach(paths, async files =>
              {
                  count++;
                  try
                  {
                      VerifiedTime(count, stopWatch, FilesSystemTXT);
                      if (File.GetAttributes(files).HasFlag(FileAttributes.Directory) )   
                          await Task.Run(()=>ReadAllRecursive(GetAllFileAndDirectory(files).ToList(),stopWatch));
                      else if (files.EndsWith(".txt")) 
                      {
                          await c(files,count); 
                      }
                  }
                  catch
                  {
                      Console.WriteLine("ERROR");
                  }
              });
            
        }
        private Task c(string route, int count){
            Console.WriteLine($"num thread {ThreadPool.ThreadCount} coun files {FilesSystemTXT.Count} directory run {count}");
                            return Task.Run(()=>FilesSystemTXT.Add(route));
        }
        private async Task ReadAllRecursiveTask(List<string> paths, Stopwatch stopWatch)
        { 
            var result = paths.Select((path) =>
            { 
                count++;
                try
                {
                    VerifiedTime(count, stopWatch, FilesSystemTXT);
                    return (path switch
                    {
                        string route when File.GetAttributes(route).HasFlag(FileAttributes.Directory) =>
                            Task.Run(()=>ReadAllRecursiveTask(GetAllFileAndDirectory(route).ToList(),stopWatch)),
                        string route when route.EndsWith(".txt") =>
                           c(route,count),
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

        private void ReadAllDirectory(List<string> paths, Stopwatch stopWatch)
        {
           
            paths.ForEach(files =>
            {
                count++;
                try
                {
                    VerifiedTime(count, stopWatch, FilesSystemTXT);
                    if (File.GetAttributes(files).HasFlag(FileAttributes.Directory))
                        ReadAllDirectory(GetAllFileAndDirectory(files).ToList(),stopWatch);
                    else if (files.EndsWith(".txt"))
                    {
                        FilesSystemTXT.Add(files);
                        Console.WriteLine($"num thread {ThreadPool.ThreadCount} coun files {FilesSystemTXT.Count} directory run {count}");
                    }
                }
                catch
                {
                    Console.WriteLine("ERROR");
                }
            });

        }
        private void VerifiedTime(int count, Stopwatch stopWatch, ConcurrentBag<string> FilesSystemTXT)
        {
            if (count == 800000)
            {
                stopWatch.Stop();
                // Get the elapsed time as a TimeSpan value.
                TimeSpan ts = stopWatch.Elapsed;

                // Format and display the TimeSpan value.
                string elapsedTime = string.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                    ts.Hours, ts.Minutes, ts.Seconds,
                    ts.Milliseconds / 10);
                Console.WriteLine($"RunTime {elapsedTime} Total element {FilesSystemTXT.Count} Total Distinct Element {FilesSystemTXT.Distinct().Count()}");
            }
            return;
        }
        private static List<string> GetAllFileAndDirectory(string path)=> Directory.GetFiles(path)
                .Concat(Directory.GetDirectories(path)).ToList();
         
    }
}
