package com.myAmway.frm.fileupload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class FileuploadProgressListener implements ProgressListener {
	
	private HttpSession session;
	
    public FileuploadProgressListener(HttpServletRequest req) {
        session=req.getSession();
        FileUploadStatus status = new FileUploadStatus();
        session.setAttribute("status", status);
    }	
	
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		FileUploadStatus status = (FileUploadStatus) session.getAttribute("status");
        status.setPBytesRead(pBytesRead);
        status.setPContentLength(pContentLength);
        status.setPItems(pItems);
	}

}
