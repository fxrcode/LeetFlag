class GeoHash:
    BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz"

    """
    chap 12: LBS-Uber 4s design
    @param: latitude: one of a location coordinate pair
    @param: longitude: one of a location coordinate pair
    @param: precision: an integer between 1 to 12
    @return: a base32 string
    """
    def encode(self, latitude, longitude, precision):
        # write your code here
        # Variable names follow the same convention as function names.
        min_lat, max_lat = -90.0, 90.0
        min_lng, max_lng = -180.0, 180.0
        bin_result = 0
        for i in range(precision*5):
            if i % 2 == 0:
                # even: long
                mid_lng = (min_lng + max_lng) / 2
                if longitude < mid_lng:
                    bin_result <<= 1
                    max_lng = mid_lng
                else:
                    bin_result <<= 1
                    bin_result |= 1
                    min_lng = mid_lng
            else:
                # odd: lat
                mid_lat = (min_lat + max_lat) / 2
                if latitude < mid_lat:
                    bin_result <<= 1
                    max_lat = mid_lat
                else:
                    bin_result <<= 1
                    bin_result |= 1
                    min_lat = mid_lat

        result = self.bin2base(bin_result, precision*5)
        return result


    def bin2base(self, bin, precision):
        """helper method to convert int to BASE32 form string
        """
        result = ""
        bin_str = format(bin, "b")
        for i in range(0, precision, 5):
            if i + 5 > precision:
                result += self.BASE32[int(bin_str[i:precision], 2)]
            else:
                result += self.BASE32[int(bin_str[i:i+5], 2)]
        return result


test = GeoHash()
print(test.encode(39.92816697, 116.38954991, 12))
print(test.encode(-90, 180, 12))
