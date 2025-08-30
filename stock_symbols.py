import pandas as pd
import time

url = "https://raw.githubusercontent.com/datasets/s-and-p-500-companies/main/data/constituents.csv"
df_sp500 = pd.read_csv(url)
#print(df_sp500.dtypes)
df_sp500_symbol = df_sp500['Symbol'] + ','
print(df_sp500_symbol)
# df_sp500_symbol.to_csv("stock_symbols", index=False)
# df_sp500_ind = df_sp500['GICS Sector']
# print(df_sp500_ind)