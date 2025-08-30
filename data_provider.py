import pandas as pd
import finnhub
import time
finnhub_client = finnhub.Client(api_key="")

url = "https://raw.githubusercontent.com/datasets/s-and-p-500-companies/main/data/constituents.csv"
df_sp500 = pd.read_csv(url)
# print(type(df_sp500))
# print((df_sp500)['Symbol'][502])
# print(len(df_sp500))

for i in range (0, len(df_sp500)):
  if (i+1) % 60 != 0:
    print("Symbol=" + (df_sp500)['Symbol'][i] + " calltime=" + (str)(i+1))
    print(finnhub_client.quote((df_sp500)['Symbol'][i]))
  else:
    print("****CALLING PAUSE FOR 60 SECONDS****")
    for j in range (0, 5):
      time.sleep(10)
      print(f"{j * 10 + 10} sec...")
    print("****CALLING RESUMED****")
    print("Symbol=" + (df_sp500)['Symbol'][i] + " calltime=" + (str)(i+1))
    print(finnhub_client.quote((df_sp500)['Symbol'][i]))
  
    
#print(type(finnhub_client.quote('AAPL')))
#print(finnhub_client.quote('AAPL')['c'])