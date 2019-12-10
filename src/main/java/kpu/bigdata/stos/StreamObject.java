package kpu.bigdata.stos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import kpu.bigdata.st.type.STObject;

public class StreamObject {
	private BufferedReader bufferedReader;
	
	public StreamObject(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}
	
	public STObject getSTObject() {
		STObject stObject = new STObject();
		if (bufferedReader == null){
			System.out.println("Read Error! File not opened!");
			return null;
		}
		String line = "";
		try {
			while ((line = bufferedReader.readLine()) != null) {
				STObject obj = STObject.getFromString(line);
				stObject.put(obj);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
