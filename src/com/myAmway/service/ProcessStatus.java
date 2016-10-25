package com.myAmway.service;

import java.io.Serializable;

public enum ProcessStatus implements Serializable{
	
	UNREADY,
	READY,
	PROCESSING,
	END,
	ERROR
	
}
