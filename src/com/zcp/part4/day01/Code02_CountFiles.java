package com.zcp.part4.day01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * [[统计目录下的文件数量]]
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回隐藏文件也算，但是文件夹不算
 */
public class Code02_CountFiles {

	/**
	 * bfs使用队列，dfs使用栈
	 * @param path
	 * @return
	 */
	private static int getFileNumber(String path) {
		File file = new File(path);
		if(!file.exists()){
			return 0;
		}else if(file.isFile()){
			return 1;
		}
		//如果是文件夹
		Queue<File> queue = new LinkedList<>();
		queue.offer(file);
		int count = 0;
		while(!queue.isEmpty()){
			File cFile = queue.poll();
			for (File listFile : cFile.listFiles()) {
				if(listFile.isFile()){
					count++;
				}else if(listFile.isDirectory()){
					queue.offer(listFile);
				}
			}

		}
		return count;
	}

	public static void main(String[] args) {
		// 你可以自己更改目录
		String path = "C:\\Users\\ZCP\\Desktop";
		System.out.println(getFileNumber(path));
	}

}