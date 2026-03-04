package com.electronic.store.exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException{

   public ResourceNotFoundException()
   {
       super("Resouce not found");
   }
   public ResourceNotFoundException(String message)
   {
       super(message);
   }
}
