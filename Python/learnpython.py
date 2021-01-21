# check Learn X in Y minutes: https://learnxinyminutes.com/docs/python/

# Dictionary
empty_dict = {}
filled_dict = {"one": 1, "two": 2, "three": 3}

## dict's key must be immutable types: ints, floats, strings, tuples!
# invalid_dict = {[1,2,3]: "123"} # ==> Raised a TypeError: unhashable type: 'list'
valid_dict = {(1,2,3): [1,2,3]}

filled_dict["one"]

list(filled_dict.keys())
# filled_dict["four"] # ==> Raised KeyError: 'four'
# Use "get()" method to avoid the KeyError
filled_dict.get("one")      # => 1
filled_dict.get("four")     # => None

# adding to dict
filled_dict["four"] = 4

# remove keys from a dict with del
del filled_dict["one"]
print(filled_dict)

# python can return different types, but it's better to be consistent
def dummy(i):
    if i == 0:
        return "hello world", 42
    else:
        return None, None, True


words = ["area","lead","wall","lady","ball"]
prefix = ''.join( w[:2] for w in words )