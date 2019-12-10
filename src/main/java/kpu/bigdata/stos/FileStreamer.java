package kpu.bigdata.stos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import kpu.bigdata.st.type.STObject;
import kpu.bigdata.st.type.STObjectViz;

public class FileStreamer {
	private String path = null;
	private File file = null;
	private BufferedReader br = null;
	private LinkedList<String> streamedData = new LinkedList<>();
	private FileStreamThread thread;
	private LiveStream app;
	
	public class FileStreamThread extends Thread{
		String res;
		boolean running;
		LiveStream app;
		STObject appObj;
		
		public FileStreamThread(LiveStream app) {
			this.running = false;
			this.res = null;
			this.app = app;
			this.appObj = new STObject();
		}
		@Override
		public void run() {
			try {
				while(!running){
					Thread.sleep(1000);
				}
				while((res=readLine()) != null){
					streamedData.add(res);
					STObject obj = STObject.getFromString(res);
					app.drawObj(obj);
					app.writeLog(res);
					appObj.setUserData(obj.getUserData());
					appObj.put(obj);
					while(!running){
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
				}
				app.writeLog("Stream Done!");
				thread.running = false;
				System.out.println(appObj.toString());
				if(file != null)
					closeFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public FileStreamer(LiveStream app, String path){
		this.path = path;
		this.app = app;
		thread = new FileStreamThread(app);
		streamedData.clear();
		thread.start();
	}
	public FileStreamer(LiveStream app){
		this.app = app;
		thread = new FileStreamThread(app);
		streamedData.clear();
		thread.start();
	}
	
	public void openFile(){
		if(this.path != null){
			openFile(path);
		}
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public void openFile(String path){
		file = new File(path);
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			file = null;
			e.printStackTrace();
		}
	}
	
	public void closeFile(){
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		br = null;
		file = null;
	}
	
	public String readLine(){
		if (br == null){
			System.out.println("Read Error! File not opened!");
			return null;
		}
		String res = null;
		try {
			res = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res == null){
			System.out.println("eof!");
		}
		return res;
	}

	public void startStream(){
		if(thread.running){
			app.writeLog("stream is already running");
			return;
		}
		if(file == null){
			app.writeLog("file not found");
			return;
		}
		app.writeLog("Stream Start");
		thread.running = true;
	}
	public void pauseStream(){
		if(!thread.running){
			app.writeLog("stream is not running");
			return;
		}
		if(file == null){
			app.writeLog("file not found");
			return;
		}
		thread.running = false;
		app.writeLog("Stream Pause");
	}
	
	public void destroy(){
		thread.stop();
	}
}
