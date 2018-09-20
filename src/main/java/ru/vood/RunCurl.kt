package ru.vood

import java.io.File
import java.util.*
import java.util.concurrent.Callable

class RunCurl : Callable<Unit> {

    fun runCurlCommand(command: String): String {
        var line = String()
        val p = Runtime.getRuntime().exec("cmd.exe /k curl.exe C:\\Curl\\curl-7.61.1-win64-mingw\\bin $command", null, File("c:\\prb"))
/*
        val inputStreamReader = InputStreamReader(p.inputStream, "Cp866")
        val br = BufferedReader(inputStreamReader)
        while (true) {
            //br.readLines()
            //println("read =" + br.read())
            if (br.read() == 99) break
            val line = br.readLine()
            if (line == null || line == "") {
                break
            }
            println(line)
        }
        p.destroyForcibly()
        inputStreamReader.close()
        br.close()
*/
        return line
        //println(p)
    }

    fun runTest1() {
        val count = 1500
        val list = arrayListOf<Long>()

        //RunCurl().runCurlCommand("--help")
        for (i in 1..count) {
            val time1 = Date().time
            //RunCurl().runCurlCommand("--help")
            runCurlCommand("http://localhost:8080/employees")
            val time2 = Date().time
            list.add(time2 - time1)
            println(Thread.currentThread().name + " i=" + i)
        }

        println("Avg = " + (list.asSequence().sum() / list.asSequence().count()))
    }

    override fun call() {
        runTest1()
    }
}

fun main(args: Array<String>) {

    RunCurl().runTest1()
/*
    val forkJoinPool = ForkJoinPool()
    val futureList = ArrayList<Future<Unit>>()
    var i = 0
    while (i < 20) {
        //FutureTask<User> futureTask = new FutureTask(application.gitHubLookupService);
        val futureTask = FutureTask(RunCurl())
        futureList.add(futureTask)

        //forkJoinPool.submit(futureTask)
        Thread(futureTask).start()
        i++
        //new Thread(futureTask).start();
    }


    while (true) {
        val first = futureList.stream().filter { userFuture -> userFuture.isDone == false }.findFirst()

        if (first.isPresent == false) {
            break
        }
        if (i % 10000000 == 0) {
            val count = futureList.stream()
                    .filter { userFuture -> userFuture.isDone == false }
                    .count()
            println("осталось отработать $count")
            i = 0
            val first1 = futureList.stream()
                    .filter { userFuture -> userFuture.isDone == true }
                    .findFirst()
            System.out.println(first1.get().get().toString())
        }
        //System.out.println(("осталось отраотать " + i));
        i++
    }
    println(i)




*/
    System.exit(0)
}