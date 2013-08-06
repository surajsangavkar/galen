/*******************************************************************************
* Copyright 2013 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.galen.specs.reader;

import java.util.LinkedList;
import java.util.List;

import net.mindengine.galen.specs.Side;

public class ExpectSides implements Expectation<List<Side>>{

    @Override
    public List<Side> read(StringCharReader reader) {
        ExpectWord expectWord = new ExpectWord();
        
        List<Side> sides = new LinkedList<Side>();
        
        while(reader.hasMore()) {
            String side = expectWord.stopOnThisSymbol(',').read(reader);
            if (!side.isEmpty()) {
                sides.add(Side.fromString(side));
            }
            if (reader.currentSymbol() == ',') {
                break;
            }
        }
        if (sides.size() == 0) {
            throw new IncorrectSpecException("There are no sides defined for location");
        }
        return sides;
    }
    
    
}