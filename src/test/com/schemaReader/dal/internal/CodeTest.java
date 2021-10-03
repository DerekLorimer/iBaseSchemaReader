package com.schemaReader.dal.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;


class CodeTest {
	
	static Logger logger = LogManager.getLogger(CodeTest.class);
	

	@Test
	void test() {
		Code code = new Code();
		
		logger.debug("{}",code.getSortedChildCodes().size());
			
		
	}

}
