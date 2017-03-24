package com.allen.test.DataStructure.MapImpl;

import java.util.HashMap;

public class HashMapOne {
	
	/** 
     * 取数组的大小为16 
     */  
    private static final int SIZE = 16;  
    private Entry table[] = new Entry[SIZE];  
  
    /** 
     * 我们要用key和value定义一个简单的Map数据结构 
     * 它还用在链表中的桶的冲突处理，如果多个键值对有 
     * 相同的hashcode,但是有不同的key，使用指针“next” 
     */  
    class Entry {  
        final String key;  
        String value;  
        Entry next;  
  
        Entry(String k, String v) {  
            key = k;  
            value = v;  
        }  
  
        public String getValue() {  
            return value;  
        }  
  
        public void setValue(String value) {  
            this.value = value;  
        }  
  
        public String getKey() {  
                return key;  
        }  
    }  
  
    /**在我们自己实现的HashMap中根据key返回value， 
     * 如果是没有和key对应的value返回空 
    */  
    public Entry get(String k) {  
        int hash = k.hashCode() % SIZE;  
        Entry e = table[hash];  
  
        //如果找到了对应的桶，那么就沿着链寻找看看与key对应的value是否存在  
        while(e != null) {  
            if(e.key.equals(k)) {  
                return e;  
            }  
            e = e.next;  
        }  
        return null;  
    }  
  
    /** 
     * 在map中将特定的key和value关联起来。 
     * 如果包含了相同的key，原来的旧值将会被替换掉 
    */  
    public void put(String k, String v) {  
        int hash=k.hashCode()%SIZE;  
        Entry e=table[hash];  
        if(e!=null){  
            //检查是否有相同的key,如果有用新值代替旧值  
            while(e.next!=null){  
                if(e.key.equals(k)){  
                    e.value=v;  
                    return ;  
                }  
                e=e.next;  
            }  
            //在链的末尾添加新的元素  
            Entry entryInOldBucket=new Entry(k,v);  
            e.next=entryInOldBucket;  
        }  
        else{  
        //建立一个新桶  
        Entry entryInNewBucket=new Entry(k,v);  
        table[hash]=entryInNewBucket;  
        }  
  
    }  

	public static void main(String[] args) {
		HashMapOne tmhm = new HashMapOne();  
        HashMap<String,Object> map = new HashMap<>();  
        map.put("Niranjan", "SMTS");  
        map.put("Ananth", "SSE");  
        map.put("Niranjan", "SMTS1");  
        map.put("Chandu", "SSE");  
        tmhm.put("Niranjan", "SMTS");  
        tmhm.put("Ananth", "SSE");  
        tmhm.put("Niranjan", "SMTS1");  
        tmhm.put("Chandu", "SSE");  
  
        //设置开始时间  
        long startTime = System.nanoTime();   
        Entry e = tmhm.get("Niranjan");  
        //设置结束时间  
        long endTime = System.nanoTime();    
        long duration = endTime - startTime;    
        System.out.println("自己实现的HashMap " + duration);    
              
        //设置开始时间  
        startTime = System.nanoTime();   
        String mapResult = (String) map.get("Niranjan");  
        //设置结束时间  
        endTime = System.nanoTime();    
        duration = endTime - startTime;    
        System.out.println("原有的java.util.HashMap " + duration);    
          
        System.out.println("自己实现的Map取值： "+e.getValue());  
        System.out.println("原有的java.util.HashMap取值： "+mapResult);  

	}

}
