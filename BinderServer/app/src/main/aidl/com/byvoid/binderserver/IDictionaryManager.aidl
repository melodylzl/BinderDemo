// IDictionaryManager.aidl
package com.byvoid.binderserver;

// Declare any non-default types here with import statements

interface IDictionaryManager {

   void add(String chinese,String english);

   String query(String chinese);
}
