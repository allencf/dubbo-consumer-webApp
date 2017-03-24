package com.allen.test.DataStructure.MapImpl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

public class HashTableImpl<K,V> implements Serializable{

	//http://blog.csdn.net/vking_wang/article/details/14166593
	
	private static final long serialVersionUID = -6391986023789861924L;
	
	transient Node<K, V>[] table;

	static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() { 
        	return key; 
        }
        
        public final V getValue() { 
        	return value; 
        }
        
        public final String toString() { 
        	return key + "=" + value; 
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
	
	
	
	/**
	 * @descripton 模拟一个存的方法
	 * @param key
	 * @param value
	 */
	public static <K,V> void tput(K key,V value){
		
	}
	
	
	
	
	/**
	 * @description 模拟一个取的方法
	 * @param key
	 * @return
	 */
	public static <K,V> V tget(K key) {
		
		return null;
	}
	
	
	/*private V putForNullKey(V value) {
        //遍历链表
		for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            if (e.getKey() == null) {
                V oldValue = e.getValue();
                e.setValue(value);
                e.recordAccess(this);
                return oldValue;
            }
        }
        modCount++;
        addEntry(0, null, value, 0);
        return null;
    }*/
	
	
	public static void testHash(String key){
		int hash = key.hashCode();
		System.out.println("key hasCode :" + hash);
	}
	
	
	public static void main(String[] args) {
		testHash("aa");
		Map<String, Object> map = new HashMap<>();
		map.put("allen", "viney");
	}

}
