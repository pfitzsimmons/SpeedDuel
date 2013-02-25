package net.pfitz.webspeed;

public abstract class Runner {
	protected abstract void runIteration() throws Exception;
	protected abstract void shutdown() throws Exception;
}
