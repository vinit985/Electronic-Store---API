[Master Spring.postman_collection.json](https://github.com/user-attachments/files/26332720/Master.Spring.postman_collection.json)
{
  "info": {
    "_postman_id": "0441f1e3-f44a-4f50-b8e0-69900821b38e",
    "name": "Master Spring",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
    "_exporter_id": "16802464"
  },
  "item": [
    {
      "name": "APIController-Response Body",
      "item": [
        {
          "name": "hello",
          "request": {
            "method": "GET",
            "header": [],
            "url": {
              "raw": "localhost:8080/hello",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "hello"
              ]
            }
          },
          "response": []
        },
        {
          "name": "getUserdata",
          "request": {
            "method": "GET",
            "header": []
          },
          "response": []
        },
        {
          "name": "createUser",
          "request": {
            "method": "POST",
            "header": [],
            "url": {
              "raw": "localhost:8080/createUser",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "createUser"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "FeedbackController",
      "item": [
        {
          "name": "getFeedBacks",
          "request": {
            "method": "GET",
            "header": [],
            "url": {
              "raw": "localhost:8080/getFeedBacks",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "getFeedBacks"
              ]
            }
          },
          "response": []
        },
        {
          "name": "New Request",
          "request": {
            "method": "POST",
            "header": [],
            "url": {
              "raw": "localhost:8080/feedbackCreated",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "feedbackCreated"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "StudentController-@RequestBody",
      "item": [
        {
          "name": "createStudent",
          "request": {
            "method": "GET",
            "header": []
          },
          "response": []
        }
      ]
    },
    {
      "name": "ProductController-@Requestparam",
      "item": [
        {
          "name": "getProduct-@Requestparam",
          "request": {
            "method": "GET",
            "header": [],
            "url": {
              "raw": "localhost:8080/getProduct",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "getProduct"
              ]
            }
          },
          "response": []
        },
        {
          "name": "checkProduct-@pathVariable",
          "request": {
            "method": "GET",
            "header": []
          },
          "response": []
        }
      ]
    },
    {
      "name": "TODO",
      "item": [
        {
          "name": "FileController",
          "item": [
            {
              "name": "UploadSingleFile",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "formdata",
                  "formdata": [
                    {
                      "key": "image",
                      "type": "file",
                      "uuid": "da46450f-7da1-45e2-80f3-5633757023a0",
                      "src": "postman-cloud:///1f0e19d1-ad1a-4f70-ab37-97f5f5313749"
                    }
                  ]
                },
                "url": {
                  "raw": "localhost:9090/upload/single",
                  "host": [
                    "localhost"
                  ],
                  "port": "9090",
                  "path": [
                    "upload",
                    "single"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "uploadMultipleFile",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "formdata",
                  "formdata": [
                    {
                      "key": "image",
                      "type": "file",
                      "uuid": "da46450f-7da1-45e2-80f3-5633757023a0",
                      "src": [
                        "postman-cloud:///1f0e1b38-37e1-4840-9712-826ec2d8c0af",
                        "postman-cloud:///1f0e19d1-ad1a-4f70-ab37-97f5f5313749"
                      ]
                    }
                  ]
                },
                "url": {
                  "raw": "localhost:9090/upload/multiple",
                  "host": [
                    "localhost"
                  ],
                  "port": "9090",
                  "path": [
                    "upload",
                    "multiple"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "getImage",
              "protocolProfileBehavior": {
                "disableBodyPruning": true
              },
              "request": {
                "method": "GET",
                "header": [],
                "body": {
                  "mode": "formdata",
                  "formdata": [
                    {
                      "key": "image",
                      "type": "file",
                      "uuid": "da46450f-7da1-45e2-80f3-5633757023a0",
                      "src": [
                        "postman-cloud:///1f0e1b38-37e1-4840-9712-826ec2d8c0af",
                        "postman-cloud:///1f0e19d1-ad1a-4f70-ab37-97f5f5313749"
                      ]
                    }
                  ]
                },
                "url": {
                  "raw": "localhost:9090/upload/get",
                  "host": [
                    "localhost"
                  ],
                  "port": "9090",
                  "path": [
                    "upload",
                    "get"
                  ]
                }
              },
              "response": []
            }
          ]
        },
        {
          "name": "CreateTODO-@RequestBody",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\r\n    \"title\": \"How are you\",\r\n    \"content\":\"If you don't have some plan we can get out\",\r\n    \"status\":\"PENDING\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:9090/todos",
              "host": [
                "localhost"
              ],
              "port": "9090",
              "path": [
                "todos"
              ]
            }
          },
          "response": []
        },
        {
          "name": "getAllTodo",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\r\n    \"title\": \"How are you\",\r\n    \"content\":\"If you don't have some plan we can get out\",\r\n    \"status\":\"PENDING\"\r\n}"
            },
            "url": {
              "raw": "localhost:9090/todos",
              "host": [
                "localhost"
              ],
              "port": "9090",
              "path": [
                "todos"
              ]
            }
          },
          "response": []
        },
        {
          "name": "getSingleTodo-@PathVariable",
          "request": {
            "method": "GET",
            "header": []
          },
          "response": []
        },
        {
          "name": "Update",
          "request": {
            "method": "PUT",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\r\n    \"title\":\"Vinit Singh\",\r\n    \"status\":\"Busy\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:9090/todos/8132",
              "host": [
                "localhost"
              ],
              "port": "9090",
              "path": [
                "todos",
                "8132"
              ]
            }
          },
          "response": []
        },
        {
          "name": "delete",
          "request": {
            "method": "DELETE",
            "header": [],
            "url": {
              "raw": "localhost:9090/todos/6671",
              "host": [
                "localhost"
              ],
              "port": "9090",
              "path": [
                "todos",
                "6671"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "ElectronicStore",
      "item": [
        {
          "name": "UserAPI",
          "item": [
            {
              "name": "CreateUser",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"name\": \"Vinit Kumar Singh\",\r\n    \"email\" : \"vinitsingh985@gmail.com\",\r\n    \"password\":\"Vinit875\",\r\n    \"about\" : \"Working in IT Company\",\r\n    \"gender\": \"Male\",\r\n    \"image\" : \"vinit.png\"\r\n}",
                  "options": {
                    "raw": {
                      "language": "json"
                    }
                  }
                },
                "url": {
                  "raw": "localhost:8787/users/create",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "users",
                    "create"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "UpdateUserByID",
              "request": {
                "method": "PUT",
                "header": []
              },
              "response": []
            },
            {
              "name": "deleteUserByID",
              "request": {
                "method": "DELETE",
                "header": [],
                "url": {
                  "raw": "localhost:8787/users/delete/7",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "users",
                    "delete",
                    "7"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "getAllUser",
              "protocolProfileBehavior": {
                "disableBodyPruning": true
              },
              "request": {
                "method": "GET",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"title\": \"How are you\",\r\n    \"content\":\"If you don't have some plan we can get out\",\r\n    \"status\":\"PENDING\"\r\n}"
                },
                "url": {
                  "raw": "localhost:8787/users/getAll?PageNo=0&PageSize=20&SortBy=UserGender&SortOrder=ASC",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "users",
                    "getAll"
                  ],
                  "query": [
                    {
                      "key": "PageNo",
                      "value": "0"
                    },
                    {
                      "key": "PageSize",
                      "value": "20"
                    },
                    {
                      "key": "SortBy",
                      "value": "UserGender"
                    },
                    {
                      "key": "SortOrder",
                      "value": "ASC"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "getUserByName",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8787/users/getByUserName/vi",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "users",
                    "getByUserName",
                    "vi"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "getUserById",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8787/users/getByUserId/1",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "users",
                    "getByUserId",
                    "1"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "getUserByEmail",
              "request": {
                "method": "GET",
                "header": []
              },
              "response": []
            },
            {
              "name": "uploadUserImage",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "formdata",
                  "formdata": [
                    {
                      "key": "userImage",
                      "type": "file",
                      "uuid": "5e441ea0-caef-424d-b59c-bbe63cd3b335",
                      "src": "/C:/Users/vinit/Downloads/OIP.jpg"
                    }
                  ]
                },
                "url": {
                  "raw": "localhost:8787/users/uploadImage/52",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "users",
                    "uploadImage",
                    "52"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "gertImage",
              "protocolProfileBehavior": {
                "disableBodyPruning": true
              },
              "request": {
                "method": "GET",
                "header": [],
                "body": {
                  "mode": "formdata",
                  "formdata": [
                    {
                      "key": "userImage",
                      "type": "file",
                      "uuid": "5e441ea0-caef-424d-b59c-bbe63cd3b335",
                      "src": "/C:/Users/vinit/Downloads/OIP.jpg"
                    }
                  ]
                },
                "url": {
                  "raw": "localhost:8787/users/getImage/52",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "users",
                    "getImage",
                    "52"
                  ]
                }
              },
              "response": []
            }
          ]
        },
        {
          "name": "Category",
          "item": [
            {
              "name": "createcategory",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"title\":\"BuddhaGG\",\r\n    \"description\":\"Life of Buddha\",\r\n    \"coverImage\":\"buddha.png\"\r\n}",
                  "options": {
                    "raw": {
                      "language": "json"
                    }
                  }
                },
                "url": {
                  "raw": "localhost:8787/category/create",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "category",
                    "create"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "update",
              "request": {
                "method": "PUT",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"title\":\"Buddha\",\r\n    \"description\":\"Life of Buddha\",\r\n    \"coverImage\":\"buddha.png\"\r\n}",
                  "options": {
                    "raw": {
                      "language": "json"
                    }
                  }
                },
                "url": {
                  "raw": "localhost:8787/category/update/1",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "category",
                    "update",
                    "1"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "deleteCategory",
              "request": {
                "method": "DELETE",
                "header": [],
                "url": {
                  "raw": "localhost:8787/category/delete/2",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "category",
                    "delete",
                    "2"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "getAll",
              "protocolProfileBehavior": {
                "disableBodyPruning": true
              },
              "request": {
                "method": "GET",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"title\":\"BuddhaGG\",\r\n    \"description\":\"Life of Buddha\",\r\n    \"coverImage\":\"buddha.png\"\r\n}"
                },
                "url": {
                  "raw": "localhost:8787/category/getAll?pageNo=1&pageSize=2&sortBy=title&sortOrder=ascc",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "category",
                    "getAll"
                  ],
                  "query": [
                    {
                      "key": "pageNo",
                      "value": "1"
                    },
                    {
                      "key": "pageSize",
                      "value": "2"
                    },
                    {
                      "key": "sortBy",
                      "value": "title"
                    },
                    {
                      "key": "sortOrder",
                      "value": "ascc"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "getCategoryById",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8787/category/getCategoryById/53",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "category",
                    "getCategoryById",
                    "53"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "uploadImage",
              "request": {
                "method": "POST",
                "header": [
                  {
                    "key": "",
                    "value": "",
                    "type": "text"
                  }
                ],
                "body": {
                  "mode": "formdata",
                  "formdata": [
                    {
                      "key": "cateGoryFile",
                      "type": "file",
                      "uuid": "50e94988-7721-43f1-95b8-bc2aebca3874",
                      "src": "postman-cloud:///1f0e1b38-37e1-4840-9712-826ec2d8c0af"
                    }
                  ]
                },
                "url": {
                  "raw": "localhost:8787/category/uploadImage/53",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "category",
                    "uploadImage",
                    "53"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "New Request",
              "request": {
                "method": "GET",
                "header": []
              },
              "response": []
            }
          ]
        },
        {
          "name": "Product",
          "item": [
            {
              "name": "Create Product",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"title\":\"IPhone 17 pro Max\",\r\n    \"description\":\"This is a mobile phone which belongs to category IPhone\",\r\n    \"price\":150000,\r\n    \"discountedPrice\":125000,\r\n    \"availableQuantity\":50,\r\n    \"live\" :true,\r\n    \"inStock\":true\r\n}",
                  "options": {
                    "raw": {
                      "language": "json"
                    }
                  }
                },
                "url": {
                  "raw": "localhost:8787/products/create",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "create"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get All Product",
              "protocolProfileBehavior": {
                "disableBodyPruning": true
              },
              "request": {
                "method": "GET",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"title\":\"IPhone 17 pro Max\",\r\n    \"description\":\"This is a mobile phone which belongs to category IPhone\",\r\n    \"price\":150000,\r\n    \"discountedPrice\":125000,\r\n    \"availableQuantity\":50,\r\n    \"live\" :true,\r\n    \"inStock\":true\r\n}"
                },
                "url": {
                  "raw": "localhost:8787/products/getAll?pageNo=0&pageSize=1&sortBy=price&sortOrder=DESC",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "getAll"
                  ],
                  "query": [
                    {
                      "key": "pageNo",
                      "value": "0"
                    },
                    {
                      "key": "pageSize",
                      "value": "1"
                    },
                    {
                      "key": "sortBy",
                      "value": "price"
                    },
                    {
                      "key": "sortOrder",
                      "value": "DESC"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get All Live Product",
              "request": {
                "method": "GET",
                "header": []
              },
              "response": []
            },
            {
              "name": "Search By title",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8787/products/search/iph",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "search",
                    "iph"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Update price of product",
              "request": {
                "method": "PUT",
                "header": [],
                "url": {
                  "raw": "localhost:8787/products/update",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "update"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get Product By Id",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8787/products/get/1",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "get",
                    "1"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Delete product By Id",
              "request": {
                "method": "DELETE",
                "header": [],
                "url": {
                  "raw": "localhost:8787/products/delete/1",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "delete",
                    "1"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Upload Image",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "formdata",
                  "formdata": [
                    {
                      "key": "file",
                      "type": "file",
                      "uuid": "cc45865e-8924-4311-ae32-38f4cd5e7280",
                      "src": "/C:/Users/vinit/Downloads/Untitled Diagram.drawio.png"
                    }
                  ]
                },
                "url": {
                  "raw": "localhost:8787/products/uploadImage/2",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "uploadImage",
                    "2"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get Image By Id",
              "request": {
                "method": "GET",
                "header": []
              },
              "response": []
            },
            {
              "name": "Create product with category Id",
              "request": {
                "method": "POST",
                "header": [],
                "body": {
                  "mode": "raw",
                  "raw": "{\r\n    \"title\":\"Honour 9 Lite\",\r\n    \"description\":\"This is a mobile phone which belongs to category IPhone\",\r\n    \"price\":15000,\r\n    \"discountedPrice\":12000,\r\n    \"availableQuantity\":50,\r\n    \"live\" :true,\r\n    \"inStock\":true\r\n}",
                  "options": {
                    "raw": {
                      "language": "json"
                    }
                  }
                },
                "url": {
                  "raw": "localhost:8787/products/create/categories/152/products",
                  "host": [
                    "localhost"
                  ],
                  "port": "8787",
                  "path": [
                    "products",
                    "create",
                    "categories",
                    "152",
                    "products"
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Update existing product with categoryId",
              "request": {
                "method": "GET",
                "header": []
              },
              "response": []
            }
          ]
        }
      ]
    }
  ]
}
