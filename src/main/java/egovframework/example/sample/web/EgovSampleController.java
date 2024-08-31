/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.cmmn.code.service.impl.ComCodeMapper;
import egovframework.config.security.impl.AuthUser;


@RestController
@RequestMapping("/api")
public class EgovSampleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleController.class);
		
	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	private final ComCodeMapper codeMapper;
	
	public EgovSampleController(ComCodeMapper codeMapper) {
		this.codeMapper = codeMapper;
	}

	@GetMapping("/test")
	public ResponseEntity<?> test() throws Exception {
		
		AuthUser user = new AuthUser.Builder()
							.email("adad")
							.password("adad")
							.build();
		
		LOGGER.debug("@@@ MAP : " + user.toString());
							
		List<EgovMap> codeList = codeMapper.selectAll();
		for(EgovMap map : codeList) {
			LOGGER.debug("@@@ MAP : " + map.toString());
		}
		
		return ResponseEntity.ok(user);
	}
}
