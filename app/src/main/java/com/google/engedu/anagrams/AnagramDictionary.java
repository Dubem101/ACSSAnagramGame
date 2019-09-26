/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private ArrayList<String> wordList = new ArrayList<>();
    private Random random = new Random();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        int position = 0;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        return true;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String tempTarget = sortLetters(targetWord);
        for(int i = 0; i < wordList.size(); i++)
        {
            if(wordList.get(i).length() == targetWord.length()) {
                String tempCompare = sortLetters(wordList.get(i));
                if (tempCompare.equals(tempTarget))
                    result.add(wordList.get(i));
            }
        }
        return result;
    }

    public String sortLetters(String unsortedWord)//counting sort
    {
        int [] count = new int[256];//dictionary is mostly lowercase but some weird characters in there
        char [] unsortedChars = unsortedWord.toCharArray();
        char [] sortedChars = new char[unsortedChars.length];

        for(int i = 0; i < count.length; i++)//initiate counts as 0
        {
            count[i] = 0;
        }

        for(int i = 0; i < unsortedChars.length; i++)//count up at the index corresponding to the char
        {
            int ascii = unsortedChars[i];
            count[ascii]++;
        }

        for(int i = 1; i < count.length; i++)//store sum of previous counts
        {
            count[i] += count[i-1];
        }

        for (int i = unsortedChars.length-1; i>=0; i--)//count[something] is 2, so output[2] = something, then count[something]
        {
            sortedChars[count[unsortedChars[i]]-1] = unsortedChars[i];
            count[unsortedChars[i]]--;
        }
        String str = new String(sortedChars);
        return str;

    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }

    public void display(int [] manta)
    {
        String line = "";
        for(int i = 0; i < manta.length; i++)
        {
             line += manta[i] + " ";
        }
        Log.i("count", line);
    }
}
