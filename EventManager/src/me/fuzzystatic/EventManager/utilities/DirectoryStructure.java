package me.fuzzystatic.EventManager.utilities;

import java.io.File;
import java.io.FilenameFilter;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.commands.events.EventName;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.constants.Strings;

public class DirectoryStructure {
	
	private EventManager plugin;
	private String [] subdirectories;
	
	public DirectoryStructure(EventManager plugin) {
		this.plugin = plugin;
		File file = new File(plugin.getDataFolder() + File.separator + EventConfigurationStructure.DIRECTORY + File.separator + EventName.getName() + File.separator + Strings.DIR_SPAWNS);
		subdirectories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});
	}
		
	public String[] getSubdirectories() {
		return subdirectories;
	}
}

/*package com.comcast.cable.Allen_Flickinger.BootfileCounter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirectoryStructure {
	
	private List<String> subdirectories = new ArrayList<String>();
	private List<String> currentSubdirectories = new ArrayList<String>();
	private List<String> files = new ArrayList<String>();
	private List<String> filesWithExtension = new ArrayList<String>();

	private DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
	private Date date = new Date();
	private String currentDate = dateFormat.format(date);
	
	public void subdirectories(String directoryName){
 
        File directory = new File(directoryName);
 
        File[] fList = directory.listFiles();
 
        for (File file : fList) {
            if (file.isDirectory()) {
            	subdirectories.add(file.toString());
            	subdirectories(file.getAbsolutePath());
            } 
        }
	}
	
	public void currentSubdirectories(String directoryName){
 
        File directory = new File(directoryName);
 
        File[] fList = directory.listFiles();
 
        for (File file : fList) {
        	long dirTimestamp = file.lastModified();
			String dirDate = dateFormat.format(dirTimestamp);
            if (file.isDirectory() && dirDate.equals(currentDate)) {
            	currentSubdirectories.add(file.toString());
            	currentSubdirectories(file.getAbsolutePath());
            } 
        }
	}
	
	public void files(String directoryName) {
		
		File directory = new File(directoryName);
		 
        File[] fList = directory.listFiles();
        
		System.out.println(fList);

		
        for (File file : fList) {
        	if (file.isFile()) {
        		files.add(file.toString());
        	} else if (file.isDirectory()) {
        		files(file.getAbsolutePath());
        	}
		}
	}
	
	public void filesWithExtension(String extension, String directoryName) {
		
		File directory = new File(directoryName);
		 
        File[] fList = directory.listFiles();
		
        for (File file : fList) {
        	if (file.isFile() && file.toString().endsWith(extension)) {
        		filesWithExtension.add(file.toString());
        	} else if (file.isDirectory()) {
        		filesWithExtension(extension, file.getAbsolutePath());
        	}
		}
	}
	
	public List<String> getSubdirectories() {
		return subdirectories;
	}
	
	public List<String> getCurrentSubdirectories() {
		return currentSubdirectories;
	}
	
	public List<String> getFiles() {
		return files;
	}
	
	public List<String> getFilesWithExtension() {
		return filesWithExtension;
	}
}
*/
