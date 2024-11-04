#See https://aka.ms/customizecontainer to learn how to customize your debug container and how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
USER app
WORKDIR /app
EXPOSE 8080
EXPOSE 8081

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
ARG BUILD_CONFIGURATION=Release
WORKDIR /src
COPY ["RV/Task340/Task340.Publisher/Task340.Publisher.csproj", "RV/Task340/Task340.Publisher/"]
RUN dotnet restore "./RV/Task340/Task340.Publisher/Task340.Publisher.csproj"
COPY . .
WORKDIR "/src/RV/Task340/Task340.Publisher"
RUN dotnet build "./Task340.Publisher.csproj" -c $BUILD_CONFIGURATION -o /app/build

FROM build AS publish
ARG BUILD_CONFIGURATION=Release
RUN dotnet publish "./Task340.Publisher.csproj" -c $BUILD_CONFIGURATION -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "Task340.Publisher.dll"]