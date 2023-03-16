FROM mcr.microsoft.com/dotnet/runtime:7.0 AS base
WORKDIR /app

FROM mcr.microsoft.com/dotnet/sdk:7.0 AS build
WORKDIR /src
COPY ["LaboratorCSharp/LaboratorCSharp.csproj", "LaboratorCSharp/"]
RUN dotnet restore "LaboratorCSharp/LaboratorCSharp.csproj"
COPY . .
WORKDIR "/src/LaboratorCSharp"
RUN dotnet build "LaboratorCSharp.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "LaboratorCSharp.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "LaboratorCSharp.dll"]
