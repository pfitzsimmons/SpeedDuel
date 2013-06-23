package net.pfitz.groovspeed;
import net.pfitz.groovspeed.josephus.JosephusRunner;
import groovy.transform.CompileStatic

@CompileStatic
public class Main
{
        public static void main(String[] arguments)
        {
            String action = System.getProperty("action")
            if (!action) {
                println("System property action is required")
                return;
            }
            BaseRunner runner;
            int numTimes = 10;
            if (action == "josephus") {
                runner = new JosephusRunner()
                numTimes = 100000
            } else if (action == "josephus-static") {

            } else if (action == "josephus-linked-list") {

            } else {
                println("Invalid action")
            }
            // First run is just to force compiling
            runner.runOnce();
            long start = System.nanoTime();
            for (int i = 0; i < numTimes; i++) {
                runner.runOnce();
            }
            String postfix = " microseconds";
            long end = System.nanoTime();
            long elapsed = end - start;
            double perIteration = elapsed / numTimes / 1000.0
            if (perIteration >= 10000) {
                perIteration = perIteration/1000.0
                postfix = " milliseconds"
            }
            println("Time per iteration = ${perIteration}${postfix}")
            runner.shutdown();

        }
}