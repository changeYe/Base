package com.ytq.design.pattern.factory.facotry;

import com.ytq.design.pattern.factory.parser.IRuleParser;

/**
 * @author yuantongqin
 * 2020-05-13
 */
public interface IRuleConfigParserFactory {


    IRuleParser createParser();

}
