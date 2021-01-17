class GeoHash:
    BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz"
    """
    @param: geohash: geohash a base32 string
    @return: latitude and longitude a location coordinate pair
    """
    def decode(self, geohash):
        # write your code here
        min_lat, max_lat = -90, 90
        min_lng, max_lng = -180, 180
        longitude, latitude = 0, 0
        bin_str = format(self.str2bin(geohash), "b")

        for i in range(len(bin_str)):
            if i % 2 == 0:
                # lng
                mid_lng = (max_lng + min_lng) / 2
                if bin_str[i] == '0':
                    max_lng = mid_lng
                else:
                    min_lng = mid_lng
            else:
                # lat
                mid_lat = (max_lat + min_lat) / 2
                if bin_str[i] == '0':
                    max_lat = mid_lat
                else:
                    min_lat = mid_lat

        longitude = (min_lng + max_lng) / 2
        latitude = (min_lat + max_lat) / 2
        return longitude, latitude

    def str2bin(self, geohash):
        bin_result = 0
        for h in geohash:
            bin_result <<= 5
            bin_result += self.BASE32.index(h)
        print(bin(bin_result))
        return bin_result

test = GeoHash()
print(test.decode("wx4g0s"))
print(test.decode("w"))