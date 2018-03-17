﻿package gz.itcast.service;

import gz.itcast.dao.FileBeanDao;
import gz.itcast.entity.FileBean;

import java.util.List;

public class FileBeanService {
	FileBeanDao dao=new FileBeanDao();
	
	public void saveFile(FileBean bean){
		dao.saveFile(bean);	
	}

	public FileBean findById(int id){
		return dao.findById(id);
	}
	
	public List<FileBean> findAll(){
		return dao.findAll();
	}
	
}


